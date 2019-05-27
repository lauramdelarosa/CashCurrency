package com.delarosa.cashcurrency.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.model.Currency
import kotlinx.android.synthetic.main.grid_item.view.*


class CurrencyAdapter(
    private val context: Context,
    var currencyList: List<Currency>,
    private val clickListener: (Currency) -> Unit
) : BaseAdapter() {


    override fun getCount(): Int {
        return currencyList.size
    }

    override fun getItem(position: Int): Any {
        return currencyList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currency = this.currencyList[position]
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflator.inflate(R.layout.grid_item, null)
        itemView.tv_currency_text.text = currency.name.removePrefix("USD")
        itemView.tv_currency_value.text = currency.transformValue.toString()
        itemView.cardview_item.setOnClickListener { clickListener(currency) }

        return itemView
    }
}