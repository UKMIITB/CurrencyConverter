package com.example.currencyconverter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.databinding.ItemRateListBinding
import com.example.currencyconverter.model.Rate
import java.math.RoundingMode
import java.text.DecimalFormat

class RateListAdapter :
    RecyclerView.Adapter<RateListAdapter.RateListViewHolder>() {

    private val rateList: MutableList<Rate> = mutableListOf()
    private var baseCurrency: String = ""
    private val rateMap: MutableMap<String, Double> =
        mutableMapOf() // mapping of currencyKeys with the currencyValue
    private var currentAmount: Double = 0.0     // the amount currently entered in editText
    private var selectedCurrency: String =
        ""   // current selected currency from drop down currencyList

    inner class RateListViewHolder(private val binding: ItemRateListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(rate: Rate) {
            binding.currencyNameTv.text = rate.currencyName

            val convertedCurrencyValue = getConvertedAmount(rate.currentValue)
            binding.currencyValueTv.text = convertedCurrencyValue.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateListViewHolder {
        val view = ItemRateListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RateListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RateListViewHolder, position: Int) {
        if (position < 0 || position >= rateList.size) {
            return
        }
        val rate = rateList[position]
        holder.bindData(rate)
    }

    override fun getItemCount(): Int {
        return rateList.size
    }

    fun updateAdapterData(
        rateList: List<Rate>,
        baseCurrency: String,
        rateMap: Map<String, Double>
    ) {
        this.rateList.clear()
        this.rateList.addAll(rateList)
        this.baseCurrency = baseCurrency
        this.rateMap.clear()
        this.rateMap.putAll(rateMap)
    }

    fun updateCurrentAmountValue(currencyAmount: Double) {
        this.currentAmount = currencyAmount
    }

    fun updateSelectedCurrency(selectedCurrency: String) {
        this.selectedCurrency = selectedCurrency
    }

    private fun getConvertedAmount(outputCurrencyRate: Double): Double {
        /* if input amount = 100 & base currency is "USD", selected currency is "JPY" & output currency is "INR"
            Assuming 1 USD = 133.3 JPY  &&  1 USD = 79.67 INR
            convertedAmount = (INR/JPY) * amount = (79.67 / 133.3) * 100 = 59.767
        * */

        val selectedCurrencyRate: Double = rateMap.getOrDefault(selectedCurrency, 1.0)
        val convertedAmount = (outputCurrencyRate / selectedCurrencyRate) * currentAmount

        val decimalFormat = DecimalFormat("#.###")
        decimalFormat.roundingMode = RoundingMode.DOWN

        return decimalFormat.format(convertedAmount).toDouble()
    }
}