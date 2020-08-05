package example.android.savemycard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*

class CardAdapter(): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var cardList =  listOf<CardModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.itemView.cardNum.text = cardList[position].cardNum
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    public fun setCard(cardList : List<CardModel>){
        this.cardList = cardList
        notifyDataSetChanged()
    }

    class CardViewHolder(view :View): RecyclerView.ViewHolder(view)


}