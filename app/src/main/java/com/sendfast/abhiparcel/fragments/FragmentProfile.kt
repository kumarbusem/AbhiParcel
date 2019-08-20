package com.sendfast.abhiparcel.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sendfast.abhiparcel.R


class FragmentProfile : Fragment() {
    companion object {

        fun newInstance(): FragmentProfile {
            return FragmentProfile()
        }
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        return inflater!!.inflate(R.layout.fragment_profile, container, false)
    }



}