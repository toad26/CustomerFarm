import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '../_models';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

  login(username: string, password: string) {
        return this.http.post<any>(`oauth/token`, `username=${username}&password=${password}&grant_type=password`,
          { headers:
              {
                // 'Accept': '',
                'Content-Type': 'application/x-www-form-urlencoded',
                 // 'Authorization' : 'Basic bXktY2xpZW50Om15LXNlY3JldA=='
              }
          })
            .pipe(map(user => {
                if (user && user.access_token) {
                    // store user details in local storage to keep user logged in
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                }

                return user;
            }));
    }

    logout() {
        // remove user data from local storage for log out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}
