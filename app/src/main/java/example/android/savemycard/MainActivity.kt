package example.android.savemycard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
   // var cardList  = arrayListOf<CardModel>()
    lateinit var cardAdapter: CardAdapter
    private var b : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // recyclerView.layoutManager = LinearLayoutManager(this)
        cardAdapter = CardAdapter()
        recyclerView.adapter = cardAdapter
        recyclerView.clipToPadding = false
        recyclerView.clipChildren = false
        recyclerView.offscreenPageLimit = 3
        recyclerView.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compostie = CompositePageTransformer()
        compostie.addTransformer(MarginPageTransformer(40))
        compostie.addTransformer(ViewPager2.PageTransformer { page, position ->
            var r = 1 - Math.abs(position)
            page.scaleY = 0.95f + r * 0.05f
        })
        recyclerView.setPageTransformer(compostie)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.allCard.observe(this, Observer { cards->
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
