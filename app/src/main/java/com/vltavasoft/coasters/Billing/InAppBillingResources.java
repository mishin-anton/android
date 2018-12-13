package com.vltavasoft.coasters.Billing;

public class InAppBillingResources {
    private static final String RSA_KEY = null;              // Ваш RSA ключ из Google Play Console
    private static final String MERCHANT_ID = null;          // Ваш Merchant id из Google Dev Console
    private static final String SKU_DISABLE_RECYCLER = "android.test.purchased"; // Ваш product_id, создается в Google Dev Console

    public static String getRsaKey() {
        return RSA_KEY;
    }

    public static String getMerchantId() {
        return MERCHANT_ID;
    }

    public static String getSkuDisableRecycler() {
        return SKU_DISABLE_RECYCLER;
    }
}
