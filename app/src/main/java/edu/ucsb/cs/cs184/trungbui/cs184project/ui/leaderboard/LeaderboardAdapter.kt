package edu.ucsb.cs.cs184.trungbui.cs184project.ui.leaderboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import edu.ucsb.cs.cs184.trungbui.cs184project.R
import edu.ucsb.cs.cs184.trungbui.cs184project.User
import org.w3c.dom.Text

class LeaderboardAdapter(
    private val context: Context,
    private val dataSource: List<User>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return dataSource.size
    }


    override fun getItem(position: Int): Any {
        return dataSource[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_leaderboard, parent, false)
        // Extracting user information to put into listView
        val userFirstName = rowView.findViewById<TextView>(R.id.name)
        userFirstName.text = dataSource[position].firstName
        val userScore = rowView.findViewById<TextView>(R.id.score)
        userScore.text = dataSource[position].score.toString()

        // User ranking info
        val rankingInfo = rowView.findViewById<TextView>(R.id.ranking)
        rankingInfo.text = (position + 1).toString()

        return rowView
    }
}