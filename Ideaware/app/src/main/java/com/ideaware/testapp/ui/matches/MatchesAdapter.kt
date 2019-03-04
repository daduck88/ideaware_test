package com.ideaware.testapp.ui.matches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ideaware.testapp.R
import com.ideaware.testapp.data.Match
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_header_match.view.*
import kotlinx.android.synthetic.main.item_match.view.*
import java.time.format.DateTimeFormatter


class MatchesAdapter(private var items: List<Match>) :
    RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {
    companion object {
        private const val ITEM = 0
        private const val ITEM_HEADER = 1
        private var formatter = DateTimeFormatter.ofPattern("MMM dd, YYYY 'at' HH:mm")
    }

    fun setData(newItems: List<Match>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return ITEM_HEADER
        }
        var lastMatch = items.get(position - 1)
        var currentMatch = items.get(position)
        if (lastMatch.getMonth() != currentMatch.getMonth()) {
            return ITEM_HEADER
        }
        return ITEM
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return if (viewType == ITEM) {
            MatchViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_match, parent, false
                )
            )
        } else {
            MatchViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_header_match, parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class MatchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: Match) = with(itemView) {
            tv_match_league.text = item.competitionStage.competition.name
            tv_match_venue.text = item.venue.name
            tv_match_date.text = item.localDate(formatter)
            tv_match_team_home.text = item.homeTeam.name
            tv_match_team_away.text = item.awayTeam.name

            tv_header_date?.text = item.getMonthYear()

            tv_match_postponed.visibility = View.INVISIBLE
            tv_match_date.setTextColor(ContextCompat.getColor(context, R.color.text_gray))
            if (item.score != null) {
                cl_match_date.visibility = View.GONE
                cl_match_scores.visibility = View.VISIBLE

                tv_match_score_home.text = item.score.home.toString()
                tv_match_score_away.text = item.score.away.toString()

                tv_match_score_home.setTextColor(ContextCompat.getColor(context, R.color.text_dark_blue))
                tv_match_score_away.setTextColor(ContextCompat.getColor(context, R.color.text_dark_blue))
                if (item.score.winner != null) {
                    if (item.score.winner == "home") {
                        tv_match_score_home
                    } else {
                        tv_match_score_away
                    }.setTextColor(ContextCompat.getColor(context, R.color.text_light_blue))
                }

            } else {
                cl_match_date.visibility = View.VISIBLE
                cl_match_scores.visibility = View.GONE
                tv_match_day_month.text = item.localDate.dayOfMonth.toString()
                tv_match_day_week.text = item.localDate.dayOfWeek.name.substring(0, 3)
                if (item.state == "postponed") {
                    tv_match_postponed.visibility = View.VISIBLE
                    tv_match_date.setTextColor(ContextCompat.getColor(context, R.color.text_alert_red))
                }
            }
        }
    }
}