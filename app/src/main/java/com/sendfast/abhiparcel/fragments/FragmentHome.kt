package com.sendfast.abhiparcel.fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.*
import com.sendfast.abhiparcel.R
import com.sendfast.abhiparcel.R.layout.fragment_home

class FragmentHome : Fragment() {

    companion object {

        fun newInstance(): FragmentHome {
            return FragmentHome()
        }
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {





        return inflater!!.inflate(fragment_home, container, false)
    }



}
