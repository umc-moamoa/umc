
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리스트 생성
        for (i in 1..10){
            val mySurvey = MySurvey(title = "사회현상에 대한 소비자 인식 $i")
            this.modelList.add(mySurvey)
        }

        binding.rvListItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListItem.setHasFixedSize(true)
        binding.rvListItem.adapter = MyRecyclerAdapter(modelList)

        // 이벤트 리스너
        binding.formListFormCreateBtn.setOnClickListener {
            val intent = Intent(this, FormCreateActivity::class.java)
            startActivity(intent)
        }

        binding.formListGoMainLl.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}