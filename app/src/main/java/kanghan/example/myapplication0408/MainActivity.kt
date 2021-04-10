package kanghan.example.myapplication0408

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private val exampleList:MutableList<ExampleItem> = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val button1 = findViewById<Button>(R.id.btn_insertItem)
        val button2 = findViewById<Button>(R.id.btn_removeItem)

        button1.setOnClickListener {
            insertItem()
        }
        button2.setOnClickListener {
            removeItem()
        }
    }

    fun insertItem(){
        val index = Random.nextInt(8)
        val newItem = ExampleItem(
            R.drawable.ic_baseline_adb_24,
            "New item at position$index",
            "Line2"
        )
        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(){
        val index = Random.nextInt(8)

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
        clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int):MutableList<ExampleItem> {

        val list:MutableList<ExampleItem> = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = when(i%3){
                0-> R.drawable.ic_baseline_adb_24
                1-> R.drawable.ic_baseline_audiotrack_24
                else-> R.drawable.ic_baseline_wb_sunny_24
            }

            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}