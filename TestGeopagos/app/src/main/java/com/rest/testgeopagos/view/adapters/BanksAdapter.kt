package com.rest.testgeopagos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.rest.testgeopagos.databinding.PartialBankItemBinding
import com.rest.testgeopagos.model.CardIssuer
import kotlin.reflect.KFunction2

class BanksAdapter(
    private val list: List<CardIssuer>,
    private val onClickMethod: KFunction2<String, String, Unit>
) :
    RecyclerView.Adapter<BanksAdapter.BanksViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PartialBankItemBinding.inflate(inflater)
        return BanksViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BanksViewHolder, position: Int) {
        val item = list[position]
       holder.bind(item, View.OnClickListener {
           onClickMethod(item.id, item.name)
       })
    }

    class BanksViewHolder(private val binding: PartialBankItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardIssuer, listener : View.OnClickListener) {
            with(binding) {
                bankName.text = item.name
                thumbnail.load(item.secureThumbnail)
                binding.container.setOnClickListener(listener)
            }
        }
    }
}