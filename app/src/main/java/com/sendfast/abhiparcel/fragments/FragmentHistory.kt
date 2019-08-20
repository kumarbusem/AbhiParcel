package com.sendfast.abhiparcel.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sendfast.abhiparcel.R

class FragmentHistory : Fragment() {
    companion object {

        fun newInstance(): FragmentHistory {
            return FragmentHistory()
        }
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        return inflater!!.inflate(R.layout.fragment_history, container, false)
    }



}