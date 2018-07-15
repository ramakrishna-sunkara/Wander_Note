package com.wonder.wandernote.services;

import io.reactivex.Observable;
import com.wonder.wandernote.model.AddNoteResponse;
import com.wonder.wandernote.model.NotesResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitService {

    //http://www.mocky.io/v2/5b49dd7e3100006b008bc084 - empty data
    //http://www.mocky.io/v2/5b4ad1f52f000083001e0e70 - data
    @GET("/v2/5b4ad1f52f000083001e0e70")
    Observable<NotesResponse> getNotes();

    //http://www.mocky.io/v2/5b4a18eb2f00004a001e0dff - data saved success
    @GET("/v2/5b4a18eb2f00004a001e0dff")
    Observable<AddNoteResponse> addNote(@Query("note_content") String noteContent);

}
