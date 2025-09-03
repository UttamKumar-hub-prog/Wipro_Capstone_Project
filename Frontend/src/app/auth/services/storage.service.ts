import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  //static belong to class and readonly this value can'tbe chaged after assigning
  //hese are keys (names) used in localStorage to store
  private static readonly TOKEN = 'token';
  private static readonly USER = 'user';

  constructor() {}
  /*window only exists in the browser indow exists, 
  this checks if the browser provides localStorage.*/
  private static isLocalStorageAvailable(): boolean {
    return (
      typeof window !== 'undefined' &&
      typeof window.localStorage !== 'undefined'
    );
  }

  static saveToken(token: string): void {
    //Makes sure the browser supports localStorage.
    if (this.isLocalStorageAvailable()) {
      //If the user was already logged in earlier, an old JWT might be there.
      //→ Removing avoids duplicate/incorrect tokens.
      window.localStorage.removeItem(StorageService.TOKEN);
      // The fresh JWT (from backend after login) is stored with the key "token".
      window.localStorage.setItem(StorageService.TOKEN, token);
    }
  }
  /*
  Yes, you already have the JWT token (which proves who the user is).
But along with the token, you often need to store some extra user details 
(like username, email, roles, etc.) that are not convenient to extract every time from the JWT.
*/
  static saveUser(user: any): void {
    if (this.isLocalStorageAvailable()) {
      window.localStorage.removeItem(StorageService.USER);
      //localStorage can only store strings, you use JSON.stringify(user)
      //  to convert the object into a string).
      window.localStorage.setItem(StorageService.USER, JSON.stringify(user));
    }
  }

  /* This will look inside the browser’s localStorage for the key token.
       If it exists → returns the JWT string.
       If it doesn’t → returns an empty string ''.
   */
  static getToken(): string {
    if (this.isLocalStorageAvailable()) {
      return localStorage.getItem(StorageService.TOKEN) || '';
    }
    return '';
  }
  /*
   This will look inside localStorage for the key user.
   If it exists → parses the stored JSON string back into an object.
   {
  "userId": 1,
  "userRole": "ADMIN"
}
*/

  static getUser(): any {
    if (this.isLocalStorageAvailable()) {
      return JSON.parse(localStorage.getItem(StorageService.USER) || '{}');
    }
    return {};
  }

/*  static getUserRole(): string {
    const user = this.getUser(); //  fetches user data from localStorage
    if (!user) return ''; //  if no user is saved, return empty string
    return user.userRole || ''; //  otherwise, return the "userRole" value from user object
  }
*/
static getUserRole(): string {
  const user = this.getUser();
   if (!user) return '';
  return user.role || ''; // ✅ match the stored property
}

  static isAdminLoggedIn(): boolean {
    const role = this.getUserRole(); // get the role of the logged-in user
    return role === 'ADMIN'; // check if that role is ADMIN
  }

  static isUserLoggedIn(): boolean {
    const role = this.getUserRole();
    return role === 'USER';
  }

  static getUserId(): string {
    const user = this.getUser(); // Step 1: Get the stored user object from localStorage
    return user ? user.id : ''; // Step 2: If user exists → return its id, else return empty string
  }

  static logout(): void {
    if (this.isLocalStorageAvailable()) {
      window.localStorage.removeItem(StorageService.TOKEN);
      window.localStorage.removeItem(StorageService.USER);
    }
  }
}
