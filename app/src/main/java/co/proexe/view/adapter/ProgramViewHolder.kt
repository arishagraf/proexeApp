package co.proexe.view.adapter

import android.text.format.DateUtils
import android.util.Log
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import co.proexe.databinding.ItemProgramTvBinding
import co.proexe.utils.Utils.getTime
import co.proexe.view.adapter.listener.ItemClickListener
import com.example.domain.model.ProgramModel
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val OLD_VALUE = "www.dropbox.com"
private const val NEW_VALUE = "dl.dropboxusercontent.com"

class ProgramViewHolder(
    private val itemBinding: ItemProgramTvBinding,
    private val itemClickListener: ItemClickListener
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(programModel: ProgramModel) {
        itemBinding.name.text = programModel.title

        val changedDropBoxUrl = programModel.imageUrl.replace(
            OLD_VALUE,
            NEW_VALUE
        )
        Picasso.get().load(changedDropBoxUrl.toUri()).into(itemBinding.image)

        val startTime = itemBinding.root.context.getTime(programModel.startTimeDateRaw)
        val endTime = itemBinding.root.context.getTime(programModel.endTimeDateRaw)
        itemBinding.time.text = "$startTime - $endTime"

        itemBinding.type.text = programModel.type
        itemBinding.progressIndicator.progress = programModel.progressPercent

        itemBinding.itemSelected.setOnClickListener {
            itemClickListener.onItemOptionsClicked()
        }
        itemBinding.container.setOnClickListener {
            itemClickListener.onItemClicked()
        }
    }
}
