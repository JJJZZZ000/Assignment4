package Question3;

import java.util.ArrayList;
import java.util.List;

public class test_q3{
    public static void main(String[] args){
        Loan l1 = new Loan();
        Loan l2 = new Loan(0.01, 1, 2000);
        Loan l3 = new Loan(0.04, 5, 1000);
        Loan l4 = new Loan(0.03, 5, 1000);
        Loan l5 = new Loan(0.02, 5, 1000);

//        l1.store();
//        l2.store();
//        l3.store();
//        l4.store();
//        l5.store();
        List<Loan> L = new ArrayList<>();
        L.add(l1);
        L.add(l2);
        L.add(l3);
        L.add(l4);
        L.add(l5);
        Loan.putAll(L);

        Loan.show();
    }
}