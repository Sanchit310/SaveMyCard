package example.android.savemycard

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_card.*


class CardFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    // var cardList  = arrayListOf<CardModel>()
    lateinit var cardAdapter: CardAdapter
    private var b : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cardAdapter = CardAdapter()
        recyclerView.adapter = cardAdapter
        recyclerView.clipToPadding = false
        recyclerView.clipChildren = false
        recyclerView.offscreenPageLimit = 2
        recyclerView.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compostie = CompositePageTransformer()
        compostie.addTransformer(MarginPageTransformer(80))
        recyclerView.setPageTransformer(compostie)

        recyclerView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                textView.text = "$position"
                Log.d("___", "yo $position")
            }

        })

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.allCard.observe(viewLifecycleOwner, Observer { cards->
            cards?.let { cardAdapter.setCard(cards) }

        })

        addBtn.setOnClickListener {
            if(b){
                val card = CardModel("6070 6625 4401 9051","05/24", "Sanchit Verma",
                    "Visa","Debit","SBI","SBIN000321", "856")
                mainViewModel.addCard(card)
                b =!b
            }else{
                val card = CardModel("6070 6625 4401 9051","05/24", "Sanchit Verma",
                    "Rupay","Debit","UCO","SBIN000321", "856")
                mainViewModel.addCard(card)
                b =!b
            }

        }


    }


}
