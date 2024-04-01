package com.example.randstadapp1


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity(),AdapterClass.OnItemClickListener {

    // variables Declaration
    lateinit var myDataList: MutableList<DataClass>
    lateinit var myAdapter: AdapterClass // Changed to MyUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        // Initialize data list and adapter
        myDataList = ArrayList()
        myAdapter = AdapterClass(myDataList, this) // Pass this as the listener

        // Find RecyclerView by ID and set its layout manager and adapter
        val myRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        myRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        myRecyclerView.adapter = myAdapter

        // Fetch data from API
        fetchData()
    }
    // Implement onItemClick method from AdapterClass.OnItemClickListener interface
    override fun onItemClick(data: DataClass) {

        // start the DetailsViewActivity by data  passed by extra
        val intent = Intent(this, DetailsViewActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    // Function to fetch data from API
    private fun fetchData() {

        // Create API call using retrofitBuilder
        val makeCall = ApiClient.retrofitBuilder.getData()

        // Enqueue the call to execute asynchronously
        makeCall.enqueue(object : Callback<List<DataClass>> {
            override fun onResponse(call: Call<List<DataClass>>?, response: Response<List<DataClass>>?)
            {
                // Handle response
                val dataList: List<DataClass>? = response?.body()
                if (dataList != null) {
                    Log.d("DataList", dataList.toString())
                    myDataList.clear() // Clear existing data in myDataList
                    myDataList.addAll(dataList) // Add new data to myDataList
                    myAdapter.setData(dataList) // Update adapter data with new dataList
                }
            }

            override fun onFailure(call: Call<List<DataClass>>?, t: Throwable?) {
                Log.i("mytag", "Error is ${t.toString()}")
            }
        })
    }
}