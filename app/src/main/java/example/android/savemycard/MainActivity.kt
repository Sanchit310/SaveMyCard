package example.android.savemycard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
   // var cardList  = arrayListOf<CardModel>()
    lateinit var cardAdapter: CardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cardAdapter = CardAdapter()
        recyclerView.adapter = cardAdapter

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.allCard.observe(this, Observer { cards->
            cards?.let { cardAdapter.setCard(cards) }

        })

        addBtn.setOnClickListener {
            val card = CardModel("Yeyeyeyeye")
            mainViewModel.addCard(card)
        }

    }
}
