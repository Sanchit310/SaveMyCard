package example.android.savemycard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add_card.*
import kotlinx.android.synthetic.main.fragment_add_card.view.*

/**
 * A simple [Fragment] subclass.
 */
class AddCardFragment : Fragment() {
    var card : CardModel? = null

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            card = AddCardFragmentArgs.fromBundle(it).CardDetails
            cardNo.setText(card?.cardNum)
        }



        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        addCardBtn.setOnClickListener {
            val carNum = cardNo.text.toString()
            val expDate = exprDate.text.toString()
            val holderName = holderNamae.text.toString()
            val cardName = cardNamee.text.toString()
            val cardType = cardType.text.toString()
            val bankName = bankName.text.toString()
            val bankIFSC = bankIfsc.text.toString()
            val cardCvv = cardCvv.text.toString()
            val mCard = CardModel(carNum,expDate,holderName,cardName,cardType,bankName,bankIFSC,cardCvv)

            if(card == null){
                mainViewModel.addCard(mCard)
                Toast.makeText(activity, "added", Toast.LENGTH_SHORT).show()
            }else{
                mCard.id = card!!.id
                mainViewModel.updateCard(mCard)
                Toast.makeText(activity, "updated", Toast.LENGTH_SHORT).show()
            }

            val action = AddCardFragmentDirections.actionAddCardFragmentToCardFragment()
            Navigation.findNavController(it).navigate(action)

        }

    }

}
