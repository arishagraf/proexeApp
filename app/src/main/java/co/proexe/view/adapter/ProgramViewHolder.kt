package co.proexe.view.adapter

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import co.proexe.databinding.ItemProgramTvBinding
import co.proexe.view.adapter.listener.ItemClickListener
import com.example.domain.model.ProgramModel
import com.squareup.picasso.Picasso

private const val OLD_VALUE = "www.dropbox.com"
private const val NEW_VALUE = "dl.dropboxusercontent.com"

class ProgramViewHolder(
    private val itemBinding: ItemProgramTvBinding,
    private val itemClickListener: ItemClickListener
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(programModel: ProgramModel) {
        itemBinding.artist.text = programModel.title
        val changedDropBoxUrl = programModel.imageUrl.replace(
            OLD_VALUE,
            NEW_VALUE
        )
        Picasso.get().load(changedDropBoxUrl.toUri()).into(itemBinding.image)
        itemBinding.time.text = programModel.startTimeDateRaw.toString()
        itemBinding.song.text = programModel.type
        itemBinding.itemSelected.setOnClickListener {
            itemClickListener.onItemOptionsClicked(true)
        }
        itemBinding.container.setOnClickListener {
            itemClickListener.onItemClicked(true)
        }
    }
}
