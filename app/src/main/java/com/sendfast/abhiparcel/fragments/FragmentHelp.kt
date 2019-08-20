package com.sendfast.abhiparcel.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sendfast.abhiparcel.R

class FragmentHelp : Fragment() {
    companion object {

        fun newInstance(): FragmentHelp {
            return FragmentHelp()
        }
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        return inflater!!.inflate(R.layout.fragment_help, container, false)
    }



}