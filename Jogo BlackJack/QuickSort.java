package BlackJackIFRN;

import java.io.*;
import java.lang.*;
import java.util.List;

public class QuickSort {
    
    public QuickSort(){
        
    }
    
    public void qsort(List<String> list) {
//    super(list);
    quicksort(list, 0, list.size()-1);
  }

  private void quicksort(List<String> list, int p, int r) {
    if (p < r) {
      int q = partition(list,p,r);
      if (q == r) {
    q--;
      }
      quicksort(list,p,q);
      quicksort(list,q+1,r);
    }
  }

  private int partition (List<String> list, int p, int r) {
    String pivot = list.get(p);
    int lo = p;
    int hi = r;

    while (true) {
      while (list.get(hi).compareTo(pivot) >= 0 &&
         lo < hi) {
    hi--;
      }
      while (list.get(lo).compareTo(pivot) < 0 &&
         lo < hi) {
    lo++;
      }
      if (lo < hi) {
        String T = list.get(lo);
        list.set(lo, list.get(hi));
        list.set(hi, T);
      }
      else return hi;
    }
  }     
}
    