package com.duclv.bookstore.servicebook.service;

import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import vn.duclv.generated.bookstore.*;

import java.util.Arrays;

@Service
public class BookServiceImpl extends BookStoreGrpc.BookStoreImplBase {
    @Override
    public void getAuthorByBookId(GetAuthorByBookIdRequest request,
                                  StreamObserver<Author> responseObserver) {

        int bookId = request.getBookId();
        Author author = findAuthorByBookId(bookId);
        responseObserver.onNext(author);
        responseObserver.onCompleted();
    }

    private Author findAuthorByBookId(int bookId) {
        //fake method. call repository  service actually...
        return Author.newBuilder()
                .setAuthorId(1)
                .setGender("female")
                .setFirstName("Duc")
                .setLastName("Lv")
                .addAllBookId(Arrays.asList(1,2,3))
                .build();
    }

    @Override
    public void getBooksByAuthorId(GetBooksByAuthorIdRequest request,
                                   StreamObserver<GetBooksByAuthorIdResponse> responseObserver) {
    }

}
