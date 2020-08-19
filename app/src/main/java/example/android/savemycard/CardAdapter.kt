package example.android.savemycard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*

class CardAdapter(val context: Context, private val mListener : OnItemClickListener): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var cardList =  listOf<CardModel>()
    var b : Boolean = true

    interface OnItemClickListener{
        fun onItemClick(card: CardModel)
        fun onItemLongClick(card: CardModel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false), mListener, cardList)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        holder.itemView.cardNum.text = card.cardNum
        holder.itemView.expDate.text = card.expDate
        holder.itemView.holderName.text = card.holderName
        holder.itemView.cardType.text = card.cardType
        holder.itemView.bankName.text = card.bankName


        if(position%3 == 0){
            holder.itemView.linearLayout.background = ContextCompat.getDrawable(context,R.drawable.card_bg_red)
        }else if(position%3 == 1){
            holder.itemView.linearLayout.background = ContextCompat.getDrawable(context,R.drawable.card_bg_blue)
        }else{
            holder.itemView.linearLayout.background = ContextCompat.getDrawable(context,R.drawable.card_bg_grey)
        }

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun setCard(cardList : List<CardModel>){
        this.cardList = cardList
        notifyDataSetChanged()
    }

    fun getCardAt(position :Int) : CardModel{
        return cardList.get(position)
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
            view.setOnLongClickListener {
                if(listener != null){
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemLongClick(cards.get(position))
                    }
                }
                true
            }
        }
    }


}