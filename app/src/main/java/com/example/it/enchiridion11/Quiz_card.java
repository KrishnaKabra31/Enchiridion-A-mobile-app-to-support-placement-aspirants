/**
 * Created by Krishna on 4/18/2017.
 */
package com.example.it.enchiridion11;

import android.view.View;
import android.widget.Button;

public class Quiz_card {
    Button b1,b2;
    private String q,a,b,c,d,Ans;
    public Quiz_card()
    {

    }

    public Quiz_card(String q, String a, String b, String c, String d, String ans) {
        this.q = q;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        Ans = ans;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }

}
