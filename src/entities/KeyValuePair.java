/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author alaaz
 */
public class KeyValuePair {
   private final int key;
   private final String value;
   private final float data;

    
   
   public KeyValuePair(int key, String value,float data) {
   this.key = key;
   this.value = value;
   this.data=data;
   }

  public int getKey()   {    return key;    }

  public String toString() {    return value;  }
  
  public float getData() {
        return data;
    }
}
