package com.example.fady.uspets.messagesModule

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fady.uspets.Owner
import com.example.fady.uspets.R
import com.example.fady.uspets.USPetsMain.PetsConstant.MESSAGE_MODEL_KEY
import com.example.fady.uspets.USPetsMain.UsPetsMainView
import com.example.fady.uspets.chatModule.ChatActivity
import kotlinx.android.synthetic.main.activity_messages.*
import javax.inject.Inject

class MessagesActivity : UsPetsMainView(), IMessageModule.IMessageView, IMessageModule.IMessageChannelClick, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var messageModuleRecyclerAdapter: MessageModuleRecyclerAdapter
    private lateinit var messagesList: MutableList<MessageAdapterItemModel>
    @Inject
    lateinit var messageModulePresenter: MessageModulePresenter

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        val daggerComp = initDaggerController(this, null)
        daggerComp.inject(this)


        // initialize recyclerview
        initRecyclerview()
        swipeRefreshMyMessages.setOnRefreshListener(this)

        messageModulePresenter.getUserForEachChannel()
    }

    private fun initRecyclerview() {
        messagesList = mutableListOf()
        swipeRefreshMyMessages.isRefreshing = true
        rvRecyclerViews.layoutManager = GridLayoutManager(this, 1)
        messageModuleRecyclerAdapter = MessageModuleRecyclerAdapter(this, messagesList, this)
        rvRecyclerViews.adapter = messageModuleRecyclerAdapter
        swipeRefreshMyMessages.isRefreshing = false
    }

    override fun onRefresh() {
        messagesList.clear()
        messageModuleRecyclerAdapter.notifyDataSetChanged()
        messageModulePresenter.getUserForEachChannel()
    }

    override fun displayChannel(channelId: String, user: Owner) {
        messagesList.add(MessageAdapterItemModel(channelId, user))
        messageModuleRecyclerAdapter.notifyDataSetChanged()
    }

    override fun showProgressDialog() {
        super.showProgressView()
    }


    override fun dismissProgressDialog() {
        swipeRefreshMyMessages.isRefreshing = false
        super.dismisProgressView()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMessageChannelClickListner(messageItemModel: MessageAdapterItemModel) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(MESSAGE_MODEL_KEY, messageItemModel)
        startActivity(intent)
    }
}
