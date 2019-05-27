package com.delarosa.cashcurrency.ui.config

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.delarosa.cashcurrency.R
import com.delarosa.cashcurrency.model.CurrencySelected


class SpinnerAdapter(
    private val mContext: Context,
    var objects: List<CurrencySelected>,
    var selectedList: ArrayList<String>,
    private val clickListener: (List<String>) -> Unit

) :
    ArrayAdapter<CurrencySelected>(mContext, 0, objects) {

    private var isFromView = false
    private var finalCheckedList = arrayListOf<String>()
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView)
    }

    private fun getCustomView(
        position: Int, _convertView: View?
    ): View {
        var convertView = _convertView
        val holder: ViewHolder
        if (convertView == null) {
            finalCheckedList= selectedList
            val layoutInflator = LayoutInflater.from(mContext)
            convertView = layoutInflator.inflate(R.layout.spinner_item, null)
            holder = ViewHolder()
            holder.mTextView = convertView!!.findViewById<View>(R.id.text) as TextView
            holder.mCheckBox = convertView.findViewById<View>(R.id.checkbox) as CheckBox
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.mTextView!!.text = objects[position].tittle

        isFromView = true
        holder.mCheckBox!!.isChecked = objects[position].selected
        isFromView = false

        holder.mCheckBox!!.tag = position


        for (selected in selectedList) {
            if (objects[position].tittle == selected) {
                objects[position].selected = true
                clickListener(finalCheckedList)
            }

        }


        holder.mCheckBox!!.setOnCheckedChangeListener { _, isChecked ->
            if (!isFromView) {
                objects[position].selected = isChecked
                if (isChecked)
                    finalCheckedList.add(objects[position].tittle)
                else
                    finalCheckedList.remove(objects[position].tittle)
                clickListener(finalCheckedList)
            }
        }
        return convertView
    }


    private inner class ViewHolder {
        var mTextView: TextView? = null
        var mCheckBox: CheckBox? = null
    }
}