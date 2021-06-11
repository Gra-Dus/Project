package com.example.firstproject.ui.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import com.example.firstproject.R
import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.ChatItem
import com.example.firstproject.ui.custom.AvatarImageView


class ChatAdapter(val listener: (ChatItem)->Unit) : RecyclerView.Adapter<ChatAdapter.ChatItemViewHolder>(){
companion object{
    private const val ARCHIVE_TYPE = 0
    private const val SINGLE_TYPE = 1
    private const val GROUP_TYPE = 2
}
    var items : List<ChatItem> = listOf()
    override fun getItemViewType(position: Int): Int = when(items[position].chatType){
        Chat.ChatType.ARCHIVE -> ARCHIVE_TYPE
        Chat.ChatType.SINGLE ->SINGLE_TYPE
        Chat.ChatType.GROUP ->GROUP_TYPE
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            SINGLE_TYPE -> SingleViewHolder(inflater.inflate(R.layout.item_char_single,parent, false))
            GROUP_TYPE -> GroupViewHolder(inflater.inflate(R.layout.item_char_group,parent, false))
            else ->SingleViewHolder(inflater.inflate(R.layout.item_char_single,parent, false))
        }

//       val convertView = inflater.inflate(R.layout.item_char_single,parent, false)
//        Log.d("Al", "onCreateViewHolder")
//        return SingleViewHolder(convertView)
    }
    override fun getItemCount(): Int = items.size
   override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
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
    abstract inner class ChatItemViewHolder(convertView: View):RecyclerView.ViewHolder(convertView),LayoutContainer{
        override val containerView: View?
            get() = itemView
        abstract fun bind(item:ChatItem, listener: (ChatItem) -> Unit)
    }
    inner class SingleViewHolder(convertView: View) : ChatItemViewHolder(convertView), ItemTouchViewHolder {
        // Не разобрался как через синтетики сделать
        private val iv_avatar = convertView.findViewById<AvatarImageView>(R.id.iv_avatar_single)
        private val tv_title = convertView.findViewById<TextView>(R.id.tv_title_single)
        private val sv_indicator = convertView.findViewById<View>(R.id.sv_indicator)
        private val tv_data = convertView.findViewById<TextView>(R.id.tv_date_single)
        private val tv_message = convertView.findViewById<TextView>(R.id.tv_message_single)
        private val tv_counter = convertView.findViewById<TextView>(R.id.tv_counter_group)
        override fun bind(item:ChatItem, listener: (ChatItem) -> Unit){
            if(item.avatar == null){
                Glide.with(itemView)
                    .clear(iv_avatar)
                iv_avatar.setInitials(item.initials)
            }else{
                Glide.with(itemView)
                    .load(item.avatar)
                    .into(iv_avatar)
            }
           sv_indicator.visibility = if(item.isOnline) View.VISIBLE else View.GONE
            with(tv_data){
                visibility = if(item.lastMessageDate != null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }
            with(tv_counter){
                visibility = if(item.messageCount>0) android.view.View.VISIBLE else android.view.View.GONE
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
    inner class GroupViewHolder(convertView: View) : ChatItemViewHolder(convertView), ItemTouchViewHolder {
        override val containerView: View?
            get() = itemView
        // Не разобрался как через синтетики сделать
        private val iv_avatar = convertView.findViewById<AvatarImageView>(R.id.iv_avatar_group)
        private val tv_title = convertView.findViewById<TextView>(R.id.tv_title_group)
        private val tv_data = convertView.findViewById<TextView>(R.id.tv_date_group)
        private val tv_counter = convertView.findViewById<TextView>(R.id.tv_counter_group)
        private val tv_message = convertView.findViewById<TextView>(R.id.tv_message_group)
        private val tv_message_author = convertView.findViewById<TextView>(R.id.tv_message_author)
        override fun bind(item:ChatItem, listener: (ChatItem) -> Unit){
                iv_avatar.setInitials(item.title[0].toString())
            with(tv_data){
                visibility = if(item.lastMessageDate != null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }
            with(tv_counter){
                visibility = if(item.messageCount>0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }
            tv_title.text = item.title
            tv_message.text = item.shortDescription
            with(tv_message_author){
                visibility = if (item.messageCount>0) View.VISIBLE else View.GONE
                text = item.author
            }
            itemView.setOnClickListener{
                listener.invoke(item)
            }
        }



        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }


    }
}