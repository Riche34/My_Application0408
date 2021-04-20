package kanghan.example.myapplication0408

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ExampleAdapter(
    private val exampleList: List<ExampleItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item,
            parent, false
        )

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        //holder.itemVIew..이렇게도 사용 가능함 다만 비효율적

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2

        if (position == 0) {
            holder.textView1.setBackgroundColor(Color.YELLOW)
        } else {

        }
        //holder.itemView.text_view1.text = currentItem.text1
    }

    override fun getItemCount() = exampleList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

