package com.example.it.enchiridion11;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Compsel.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Compsel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Compsel extends Fragment implements quizfrag.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static String s;
    private OnFragmentInteractionListener mListener;

    public Compsel() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Compsel.
     */
    // TODO: Rename and change types and number of parameters
    public static Compsel newInstance(String param1, String param2) {
        Compsel fragment = new Compsel();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        s="";
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_compsel, container, false);
        final Button b1=(Button)v.findViewById(R.id.Siemens);
        Button b2=(Button)v.findViewById(R.id.Wipro);
        Button b3=(Button)v.findViewById(R.id.ibm);
        Button b4=(Button)v.findViewById(R.id.infosys);
        Button b5=(Button)v.findViewById(R.id.tcs);
        final Fragment fragment=new quizfrag();

        final FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s="Siemens";
                startActivity(new Intent(getActivity(),Quiz_activity.class));

            }


        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s="Wipro";
                startActivity(new Intent(getActivity(),Quiz_activity.class));


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s="IBM";
                startActivity(new Intent(getActivity(),Quiz_activity.class));
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s="Infosys";
                startActivity(new Intent(getActivity(),Quiz_activity.class));
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s="TCS";
                startActivity(new Intent(getActivity(),Quiz_activity.class));
            }
        });
        return v;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
public static String returnS()
{
    return s;
}
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
