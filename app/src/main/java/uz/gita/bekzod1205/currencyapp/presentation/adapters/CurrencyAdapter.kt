package uz.gita.bekzod1205.currencyapp.presentation.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.bekzod1205.currencyapp.R
import uz.gita.bekzod1205.currencyapp.databinding.ItemCurrencyBinding
import uz.gita.bekzod1205.currencyapp.domain.param.CurrencyData

class CurrencyAdapter(private val list: List<CurrencyData>) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyVH>() {

    private var onItemClick: ((CurrencyData) -> Unit)? = null

    fun setOnItemClickListener(block: (CurrencyData) -> Unit) {
        onItemClick = block
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CurrencyVH {
        val binding = ItemCurrencyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CurrencyVH(binding)
    }

    override fun onBindViewHolder(
        holder: CurrencyVH, position: Int
    ) = holder.bind()

    override fun getItemCount(): Int = list.size

    inner class CurrencyVH(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val data = list[absoluteAdapterPosition]
            binding.tvCurrencyCode.text = data.shortTitle
            binding.tvCurrencyName.text = data.title
            binding.tvCurrencyValue.text = data.currency
            binding.tvPercentage.text = formatPercentageChange(
                data.currency.toDouble() - data.diff.toDouble(), data.currency.toDouble()
            )
            if (data.diff.toDouble() < 0) {
                binding.imgArrow.setImageResource(R.drawable.arrow_down)
                binding.tvPercentage.setTextColor(Color.RED)
            } else {
                binding.tvPercentage.setTextColor(Color.GREEN)
                binding.imgArrow.setImageResource(R.drawable.arrow_up)
            }


            Glide.with(binding.imgFlag.context)
                .load("https://flagsapi.com/${data.shortTitle.take(2)}/flat/64.png").circleCrop()
                .placeholder(R.drawable.ic_launcher_background).into(binding.imgFlag)
            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }

    }

    private fun formatPercentageChange(prev: Double, current: Double): String {
        if (prev == 0.0) return "0.00%"
        val change = ((current - prev) / prev) * 100
        return String.format("%.2f%%", change)
    }
}