INSTALLATION GUIDE - Server + Admin + Companion App

1. Copy server_flask folder to hosting (Railway/Render recommended free tier)
2. Install requirements:
   python3 -m venv venv
   source venv/bin/activate
   pip install -r requirements.txt
3. Put Firebase service account JSON as firebase_service_account.json
4. Set ADMIN_KEY environment variable
5. Run: python app.py (server accessible via HTTPS on Railway/Render)
6. Access admin panel: https://YOUR_SERVER_URL/admin/devices?admin_key=YOUR_KEY
7. Compile companion app and install on client devices
8. Companion app sends deviceId + FCM token to server on activation
9. Admin can revoke licenses anytime from admin panel, push messages deactivate client features
