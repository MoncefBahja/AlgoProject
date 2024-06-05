package org.example.expoarrayquicksort;

public class SortAscending {

   /** private static void merge(double[] a, int lo, int mid, int hi) {
        double[] aux = new double[a.length];
        // Copy elements to auxiliary array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static void sort(double[] a, double[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static double[] sort(double[] a) {
        double[] aux = new double[a.length];
        sort(a, aux, 0, a.length - 1);
        return a;
    }

    private static boolean less(double v, double w) {
        return v - w < 0;
    }
**/

        private static boolean less (Comparable v , Comparable w) {
            return v.compareTo(w)<0;
        }


        private static boolean isSorted(double [] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++) {
                if (less(a[i], a[i - 1])) {
                    return false;
                }
            }
            return true;
        }

        private static void merge (double [] a ,double [] aux , int lo , int mid , int hi) {
            assert isSorted (a, lo , mid ) ;
            assert isSorted (a, mid+1 , hi ) ;
            for (int k = lo ; k <= hi ; k++ )
                aux [k] = a[k] ;
            int i=lo , j=mid+1	;
            for (int k=lo ; k<=hi ;k++)
            {
                if (i>mid) a[k] =aux[j++] ;
                else if (j>hi) a[k] =aux[i++] ;
                else if (less(aux[j],aux[i])) a[k] =aux[j++] ;
                else  a[k] =aux[i++] ;

            }
            assert isSorted (a,lo,hi) ;

        }


        private static void sort (double [] a ,double [] aux , int lo , int hi  )
        {
            if(hi<=lo ) return ;
            int mid = lo + (hi - lo)/ 2 ;
            sort (a,aux , lo , mid ) ;
            sort (a, aux ,mid+1,hi) ;
            merge (a, aux , lo , mid, hi) ;



        }

        public static double[] sort (double [] a) {
            double[] aux = new double [a.length] ;
            sort (a,aux,0, a.length-1) ;
            return  aux ;
        }

    }


