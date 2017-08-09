package cn.mcg.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/**
 * Author: mac
 * Date: 2017/7/23
 * Description: todo
 */
public class Solution {
    @Test
    /**
     * 返回数组总总和等于target的下标
     */
    public void twoSums(){
        int[] nums = new int[]{1,4,6,7,8};
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(twoSum(nums, 13)));
    }
    public int[] twoSum(int[] nums, int target) {
        int [] index = new int [2];
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i] + nums[j] == target){
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }
    @Test
    /**
     * A + B问题
     */
    public void AB(){
        System.out.println("请输入字符:");
        Scanner s = new Scanner(System.in);
        String[] num = s.next().split(" ");
        System.out.println(Integer.parseInt(num[0])+Integer.parseInt(num[1]));
    }

    public static void main(String[] args) {
        System.out.println("请输入字符:");
        Scanner s = new Scanner(System.in);
//        System.out.println(s.nextLine());
        String[] num = s.nextLine().toString().split(" ");
        System.out.println(Integer.parseInt(num[0])+Integer.parseInt(num[1]));
    }
    @Test
    public void toBinary(){
    		int a = 5;
    		List<Integer> bits = new ArrayList<>();
    		do{
    			if(a%2 ==0){
    				bits.add(0);
    			}else{
    				bits.add(1);
    			}
    			a = a/2;
    			
    		}while(a !=0);
    		Collections.reverse(bits);
    		System.out.println(bits.size());
    		for (int i = 0; i < bits.size(); i++) {
        		System.out.println(bits.get(i));

		}
    }

}
