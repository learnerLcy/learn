package com.test.Algorithm;

/**
 * @ClassName:Algorithm
 * @Description: 03数组中重复的数字：时间复杂度O(n),空间复杂度O(1)
 *              在一个长度为n的数组里，所有数字都在0～n-1的范围内。
 *     数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了多少次。
 *     请找出数组中任意一个重复的数字。
 *     空间复杂度为O(1)
 * @Author:lvchunyang
 * @Date:13:22
 **/
public class Algorithm03 {

    public static boolean duplicate(int[] nums) {
        int length = nums.length;
        if  (nums == null || length <= 0)
            return false;
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if  (nums[i]<0 || nums[i]>nums.length-1){
                    System.out.println("error nums");
                }
                if (nums[i] == nums[nums[i]]) {
                    System.out.println("第一个重复的值为"+nums[i]);
                    return true;
                }
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    
    
    public static void main(String[] args){
        int[] nums = new int[]{0,6,3,3,4,5,6,7,1,8,8};
        System.out.println(duplicate(nums));
    }
}
