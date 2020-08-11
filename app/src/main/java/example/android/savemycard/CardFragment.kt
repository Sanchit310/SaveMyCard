package example.android.savemycard

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_card.*


class CardFragment : Fragment(), CardAdapter.OnItemClickListener {
    private lateinit var mainViewModel: MainViewModel
    // var cardList  = arrayListOf<CardModel>()
    lateinit var cardAdapter: CardAdapter
    private var b : Boolean = true
    lateinit var  navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cardAdapter = CardAdapter(this)
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
            val action  = CardFragmentDirections.actionCardFragmentToAddCardFragment()
            Navigation.findNavController(it).navigate(action)


        }


    }

    override fun onItemClick(card: CardModel) {
        val action = CardFragmentDirections.actionCardFragmentToAddCardFragment(card)
       navController.navigate(action)
     //   Toast.makeText(activity, "Clicked ${card.cardNum}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(card: CardModel) {
        mainViewModel.deleteCard(card)
        Toast.makeText(activity, "Clicked ${card.cardNum}", Toast.LENGTH_SHORT).show()
    }


}
