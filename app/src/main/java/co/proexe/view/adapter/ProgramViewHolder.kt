package co.proexe.view.adapter

import androidx.recyclerview.widget.RecyclerView
import co.proexe.databinding.ItemProgramTvBinding
import co.proexe.view.adapter.listener.ItemClickListener
import com.example.domain.model.ProgramModel
import com.squareup.picasso.Picasso

class ProgramViewHolder(
    private val itemBinding: ItemProgramTvBinding,
    private val itemClickListener: ItemClickListener
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(programModel: ProgramModel) {
        itemBinding.artist.text = programModel.title
        Picasso.get().load(programModel.imageUrl).into(itemBinding.image)
        // itemBinding.time.text = programModel.startTime.toString()
        itemBinding.song.text = programModel.type
        itemBinding.itemSelected.setOnClickListener {
            itemClickListener.onItemOptionsClicked(true)
        }
        itemBinding.container.setOnClickListener {
            itemClickListener.onItemClicked(true)
        }
    }
}
