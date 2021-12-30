import com.example.random.service.RandomNumberService;

module com.example.lottery {
	requires com.example.random;
	uses RandomNumberService;
}