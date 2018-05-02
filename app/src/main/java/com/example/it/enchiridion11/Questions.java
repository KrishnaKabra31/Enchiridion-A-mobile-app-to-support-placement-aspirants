package com.example.it.enchiridion11;

/**
 * Created by Krishna on 4/17/2017.
 */

public class Questions {

    public Questions()
    {

    }

    private String Ans;
    private String q;

    public Questions(String ans, String q) {
        Ans = ans;
        this.q = q;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }


}
