package com.vltavasoft.coasters.Billing;

public class InAppBillingResources {
    private static final String RSA_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhKSrEbWdAzFYGuw9ZvNUBg+RQFP62C45fsMH/2DMuDhZW3BFYTscrgmG1HKSjaNHBG9yF3MSgRGjLdniURnh27pIADTyg2v4dyExYfjfN1t2B1/xfxYn4c2ZDOy2ohgp/TNyADfLX5ltVTjPaayYoEbmgbXrDKmycOXVfkIS6FnyyrESrnZJ2cJsv/FPpozgUWV3ADW5Dxn11PuZOG3Lp7Y/EUBhYuZZ7QAlR84NqwQgQ9H5ZQL/jlsuJH9H1MmEDWW1Xp6L14nPeq6XjiXDSuAtNdrQ4GtneTw4GAAYj4YC1XIMPwRB60hsI+Ke9rhPE6kkzSDzXITFkh/UTh69gQIDAQAB";              // Ваш RSA ключ из Google Play Console
    private static final String MERCHANT_ID = "05745984137275928158";          // Ваш Merchant id из Google Dev Console
    private static final String SKU_UNBLOCK = "unblock_all"; // Ваш product_id, создается в Google Dev Console

    public static String getRsaKey() {
        return RSA_KEY;
    }

    public static String getMerchantId() {
        return MERCHANT_ID;
    }

    public static String getSkuUnblock() {
        return SKU_UNBLOCK;
    }
}
