package example.android.savemycard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*

class CardAdapter(private val mListener : OnItemClickListener): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var cardList =  listOf<CardModel>()
    var b : Boolean = true

    interface OnItemClickListener{
        fun onItemClick(card: CardModel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false), mListener, cardList)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.itemView.cardNum.text = cardList[position].cardNum+"\n"+cardList[position].holderName+"\n"+cardList[position].expDate+
        "\n"+cardList[position].cardName+"\t"+cardList[position].cardType+"\n"+cardList[position].bankName+"\t"+cardList[position].cardCvv+
                "\n"+cardList[position].bankIfsc

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    public fun setCard(cardList : List<CardModel>){
        this.cardList = cardList
        notifyDataSetChanged()
    }
    class CardViewHolder(view :View, listener: OnItemClickListener, cardList : List<CardModel>): RecyclerView.ViewHolder(view){
        var cards = cardList
        init {
            view.setOnClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(cards.get(position))
                    }
                }
            }
        }
    }


}