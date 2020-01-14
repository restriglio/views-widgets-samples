package com.rest.testgeopagos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.rest.testgeopagos.databinding.PartialPaymentMethodBinding
import com.rest.testgeopagos.model.PaymentMethod
import kotlin.reflect.KFunction2

class PaymentMethodsAdapter(
    private val list: List<PaymentMethod>,
    private val onClickMethod: KFunction2<String, String, Unit>
) :
    RecyclerView.Adapter<PaymentMethodsAdapter.PaymentMethodsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = PartialPaymentMethodBinding.inflate(inflater)
        return PaymentMethodsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PaymentMethodsViewHolder, position: Int) {
        val item = list[position]
        holder.bind(list[position], View.OnClickListener {
            onClickMethod(item.id, item.name)
        })
    }

    class PaymentMethodsViewHolder(private val binding: PartialPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PaymentMethod, listener : View.OnClickListener) {
            with(binding) {
                paymentTitle.text = item.name
                paymentMethodText.text = item.status
                thumbnail.load(item.secureThumbnail)
                binding.container.setOnClickListener(listener)
            }
        }
    }
}