package com.example.fady.uspets.chatModule

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.MESSAGE_TYPE_TEXT
import com.example.fady.uspets.R
import com.example.fady.uspets.USPetsMain.PetsConstant
import com.example.fady.uspets.USPetsMain.UsPetsMainView
import com.example.fady.uspets.messagesModule.MessageAdapterItemModel
import com.example.fady.uspets.messagesModule.MessageModel
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject


class ChatActivity : UsPetsMainView(), IChatModule.IChatView {
    private lateinit var chatModuleRecyclerAdapter: ChatModuleRecyclerAdapter
    private lateinit var mutableList: MutableList<MessageModel>
    @Inject
    lateinit var chatPresenter: ChatPresenter
    private lateinit var messageModelItemModel: MessageAdapterItemModel

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        messageModelItemModel = intent.getParcelableExtra(PetsConstant.MESSAGE_MODEL_KEY)!!
        supportActionBar?.title = messageModelItemModel.userData?.getoName()
        val daggerCom = initDaggerController(this, null)
        daggerCom.inject(this)
        chatPresenter.setMessageItemModel(messageModelItemModel)


        rvChatModule.layoutManager = GridLayoutManager(this, 1)
        mutableList = mutableListOf()
        chatModuleRecyclerAdapter = ChatModuleRecyclerAdapter(this, mutableList)
        rvChatModule.adapter = chatModuleRecyclerAdapter


        chatPresenter.getChatChannel(messageModelItemModel.channelId!!)
        btnSendClick()
        etClick()

    }

    private fun btnSendClick() {
        btnSend.setOnClickListener {
            chatPresenter.onSendMessage(etChatMessage.text.toString(), MESSAGE_TYPE_TEXT)
        }
    }

    private fun etClick() {
        rvChatModule.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                if (bottom < oldBottom) {
                    rvChatModule.postDelayed(Runnable {
                        rvChatModule.smoothScrollToPosition(
                                mutableList.size - 1)
                    }, 100)
                }
            }

        })

//        etChatMessage.setOnFocusChangeListener { view: View, b: Boolean ->
//            if (b)
//                rvChatModule.postDelayed(Runnable {
//                    rvChatModule.smoothScrollToPosition(
//                            mutableList .size- 1)
//                }, 100)
//        }
    }

    override fun onChannelSuccess(messageModel: MessageModel) {
        mutableList.add(messageModel)
        chatModuleRecyclerAdapter.notifyDataSetChanged()
        rvChatModule.smoothScrollToPosition(mutableList.size - 1)
    }

    override fun onSendMessageSuccess() {
        etChatMessage.text?.clear()
        closeKeyboard()
        rvChatModule.smoothScrollToPosition(mutableList.size - 1)
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
