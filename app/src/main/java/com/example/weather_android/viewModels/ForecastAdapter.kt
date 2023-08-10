package com.example.weather_android.viewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_android.R
import com.example.weather_android.models.daily_api_models.Daily

class ForecastAdapter(private val mList: List<Daily>
) : RecyclerView.Adapter<ForecastAdapter.viewHolder>() {
    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
           val textDay: TextView = itemView.findViewById((R.id.textDay))
           val textTime: TextView = itemView.findViewById((R.id.texTime))
           val textCelsius: TextView = itemView.findViewById((R.id.textCelsius))
           val forecastImage: ImageView = itemView.findViewById((R.id.imageView))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_item,parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var forecast = mList[position]

        holder.textDay.setText(forecast.getDay())
        holder.textTime.setText(forecast.getDate())
        holder.textCelsius.setText(forecast.temp?.day?.toInt().toString()+" º")
        val resID: Int = holder.itemView.context.resources.getIdentifier("a${forecast.getWeatherItem()!!.icon}_svg", "drawable", holder.itemView.context.packageName)
        holder.forecastImage.setImageResource(resID)
    }
}

