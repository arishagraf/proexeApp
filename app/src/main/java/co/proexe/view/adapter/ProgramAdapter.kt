package co.proexe.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.proexe.databinding.ItemProgramTvBinding
import co.proexe.view.adapter.listener.ItemClickListener
import com.example.domain.model.ProgramModel

class ProgramAdapter(
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<ProgramViewHolder>() {

    private val items = mutableListOf<ProgramModel>()

    fun submitList(newItemRecommended: List<ProgramModel>) {
        items.clear()
        items.addAll(newItemRecommended)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgramViewHolder {
        val itemBinding =
            ItemProgramTvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ProgramViewHolder(itemBinding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}
