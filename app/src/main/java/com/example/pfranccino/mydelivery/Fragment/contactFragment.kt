package com.example.pfranccino.mydelivery.Fragment


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pfranccino.mydelivery.Models.User
import com.example.pfranccino.mydelivery.R
import android.widget.TextView




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class contactFragment : Fragment() {


    private var nameFragTxt: TextView? = null
    private val yearFragTxt: TextView? = null


    class contactFragment(){}




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

         val view = inflater.inflate(R.layout.fragment_contact, container, false)


        if (arguments != null) {

            val data = this.arguments?.getSerializable("objeto") as User

            nameFragTxt  = view.findViewById(R.id.textView4) as TextView


            nameFragTxt!!.text = "Que chucha"


            Log.d("Dato", data.first_name)


        }



        return view


    }


}
