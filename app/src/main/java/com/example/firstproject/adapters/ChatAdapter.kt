package com.example.firstproject.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_char_single.*
import com.example.firstproject.R
import com.example.firstproject.models.data.ChatItem
import com.example.firstproject.ui.custom.AvatarImageView



class ChatAdapter(val listener: (ChatItem)->Unit) : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>(){
var items : List<ChatItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
val inflater = LayoutInflater.from(parent.context)
       val convertView = inflater.inflate(R.layout.item_char_single,parent, false)
        Log.d("Al", "onCreateViewHolder")
        return SingleViewHolder(convertView)
    }
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        Log.d("Al", "onBindViewHolder $position")
        holder.bind(items[position], listener)

    }
    fun updateData(data:List<ChatItem>){
        val diffCallback = object : DiffUtil.Callback(){
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =items[oldPos].id == data[newPos].id

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean = items[oldPos].hashCode() == data[newPos].hashCode()

            override fun getOldListSize(): Int =items.size

            override fun getNewListSize(): Int = data.size

        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items=data
        diffResult.dispatchUpdatesTo(this)
    }
    inner class SingleViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView), LayoutContainer, ItemTouchViewHolder {
        // Не разобрался как через синтетики сделать
        private val iv_avatar = convertView.findViewById<AvatarImageView>(R.id.iv_avatar_single)
        private val tv_title = convertView.findViewById<TextView>(R.id.tv_title_single)
        private val sv_indicator = convertView.findViewById<View>(R.id.sv_indicator)
        private val tv_data = convertView.findViewById<TextView>(R.id.tv_date_single)
        private val tv_message = convertView.findViewById<TextView>(R.id.tv_message_single)
        fun bind(item:ChatItem, listener: (ChatItem) -> Unit){
            if(item.avatar == null){
                iv_avatar.setInitials(item.initials)
            }else{
                //TODO set drawable
            }
           sv_indicator.visibility = if(item.isOnline) View.VISIBLE else View.GONE
            with(tv_data){
                visibility = if(item.messageCount>0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }

            tv_title.text = item.title
            tv_message.text = item.shortDescription
            itemView.setOnClickListener{
                listener.invoke(item)
            }
        }

        override val containerView: View?
            get() = itemView

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }


    }
}