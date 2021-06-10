package com.example.firstproject.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstproject.R
import com.example.firstproject.models.data.User
import com.example.firstproject.models.data.UserItem
import com.example.firstproject.ui.custom.AvatarImageView
import kotlinx.android.extensions.LayoutContainer

class UserAdapter(val listener:(UserItem)->Unit):RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var items:List<UserItem> = listOf()
    inner class UserViewHolder(convertView: View):RecyclerView.ViewHolder(convertView),LayoutContainer{
      private  val iv_avatar_user = convertView.findViewById<AvatarImageView>(R.id.iv_avatar_user)
        private val sv_indicator = convertView.findViewById<View>(R.id.sv_indicator)
        private val tv_user_name = convertView.findViewById<TextView>(R.id.tv_user_name)
        private val tv_last_activity = convertView.findViewById<TextView>(R.id.tv_last_activity)
        private val iv_selected = convertView.findViewById<TextView>(R.id.iv_selected)
        fun bind(user:UserItem, listener:(UserItem)->Unit){
if(user.avatar != null){
    Glide.with(itemView)
        .load(user.avatar)
        .into(iv_avatar_user)
}else{
    Glide.with(itemView).clear(iv_avatar_user)
    iv_avatar_user.setInitials(user.initials ?: "??")
}
            sv_indicator.visibility = if(user.isOnline) View.VISIBLE else View.GONE
            tv_user_name.text = user.fullName
            tv_last_activity.text = user.lastActivity.toString()
            iv_selected.visibility = if(user.isSelected) View.VISIBLE else View.GONE
            itemView.setOnClickListener{listener.invoke(user)}
        }



        override val containerView: View?
            get() = itemView

    }
fun updateData(data:List<UserItem>){
    val diffCallback = object : DiffUtil.Callback(){
        override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =items[oldPos].id == data[newPos].id

        override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean = items[oldPos].hashCode() == data[newPos].hashCode()

        override fun getOldListSize(): Int =items.size

        override fun getNewListSize(): Int = data.size

    }}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val converView = inflater.inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(converView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = items.size
}