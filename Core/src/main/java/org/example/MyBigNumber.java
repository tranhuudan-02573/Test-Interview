package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyBigNumber {
    static Logger logger = LoggerFactory.getLogger(MyBigNumber.class);

    public String sum(String stn1, String stn2) {
        int num1 = Integer.parseInt(stn1);
        int num2 = Integer.parseInt(stn2);

        char[] nums1 = String.valueOf(num1).toCharArray();
        char[] nums2 = String.valueOf(num2).toCharArray();

        int lenMax = Math.max(nums1.length, nums2.length);
        int[] memoriesNum = new int[lenMax];
        StringBuilder rs = new StringBuilder();

        nums1 = normalizeArray(nums1, lenMax);
        nums2 = normalizeArray(nums2, lenMax);

        for (int i = lenMax - 1; i >= 0; i--) {
            int sum = Integer.parseInt(String.valueOf(nums1[i])) + Integer.parseInt(String.valueOf(nums2[i])) + memoriesNum[i];
            int digit = sum;
            if (sum >= 10 && i != 0) {
                digit = sum % 10;
                memoriesNum[i - 1] = 1;
                logger.info("Location: {} with value: {}", i, sum);
            }
            rs.insert(0, digit);
        }

        return rs.toString();
    }

    private static char[] normalizeArray(char[] arr, int targetLength) {
        if (arr.length == targetLength) {
            return arr;
        }
        char[] result = new char[targetLength];
        int padding = targetLength - arr.length;
        for (int i = 0; i < padding; i++) {
            result[i] = '0';
        }
        System.arraycopy(arr, 0, result, padding, arr.length);
        return result;
    }

}
