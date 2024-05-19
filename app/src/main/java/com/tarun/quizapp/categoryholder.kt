import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tarun.quizapp.databinding.TopicBinding
import com.tarun.quizapp.quiztime

class categoryholder(val context: Context, private val categories: List<String>) : RecyclerView.Adapter<categoryholder.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: TopicBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = TopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.sno.text = ("  " + (position + 1).toString()+".")
        holder.binding.topic.text = categories[position]
        holder.binding.linearlayout.setOnClickListener{
            val intent = Intent(context, quiztime::class.java)
            intent.putExtra("selectedCategory", categories[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
