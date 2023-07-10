package org.example;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//numberOfDaysPerCity = 2;
//guestBudget=309

public class TaleOfNCities {
    public List<Integer> solve(Map<String, Hotel> hotels, int numberOfDaysPerCity, int guestBudget) {
        Map<String,List<Integer>> hMap = new HashMap<>();

        for(Map.Entry<String, Hotel> entry:hotels.entrySet())
        {
            List<Integer> temp = new ArrayList<>();
            String key = entry.getKey();
            Hotel ht = entry.getValue();
            List<Integer> ar= new ArrayList<>();
            for(int i=0;i<ht.getArr().length;i++)
            {
                ar.add(ht.getArr()[i]);
            }
            List<List<Integer>> partitions = new ArrayList<>();
            for (int i = 0; i < ar.size(); i += numberOfDaysPerCity) {
                partitions.add(ar.subList(i, Math.min(i + numberOfDaysPerCity,
                        ar.size())));

            }
            int total =0;
            for(int i=0;i<partitions.size();i++)
            {
                for(Integer in:partitions.get(i))
                {
                    total += in;
                }
                temp.add(total);
            }
            hMap.put(key, temp);

        }
        int h = 0;
        Set<Integer> s=new TreeSet<>();
        for(int i=0;i<hMap.size();i++)
        {
            int total = 0;
            int index = 0;
            for(Map.Entry<String, List<Integer>> entry:hMap.entrySet())
            {
                List<Integer> ht = entry.getValue();
                total += total + ht.get(index);
                index++;

            }
            s.add(total);
        }
        return new ArrayList<>(s);
    }

    public static void main(String args[] ) throws Exception {
        // Example input
        // number
        Map<String, Hotel> hotels = new HashMap<>();
        hotels.put("Paris", new Hotel(new int[]{10,40,5,80,10,50}));
        hotels.put("London", new Hotel(new int[]{60,30,20,70,50,70}));
        hotels.put("Amsterdam", new Hotel(new int[]{20,80,20,50,80,100}));

        List<Integer> trips = new TaleOfNCities().solve(hotels, 2, 309);
        System.out.println(trips);
    }


}

class Hotel
{
    private int[] arr;
    public Hotel(int[] arr)
    {
        this.arr = arr;
    }
    public int[] getArr()
    {
        return arr;
    }
}

