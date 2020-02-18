package com.example.fady.uspets.MainScreenModule.CreateAdModule;

import java.util.Random;
import java.util.UUID;

public class CreateAdConstant {
    public final static int PET_PIC_RESULT_CODE = 2;
    public final static String PET_IMAGES_FOLDER = "PetImages";

    public static String generateRandomID() {
        return UUID.randomUUID().toString();
    }
}
